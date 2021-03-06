package edu.wctc.bean.demo5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jlombardo
 */
@Repository("springJDBCEmployeeDAO")
@Scope("singleton")
public class SpringJDBCEmployeeDAO implements IEmployeeDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private JdbcTemplate jdbcTemplate;
    @Value("${db.vendor}")
    private String dbVendor;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> findAll() throws DataAccessException {
        List<Employee> employeeList = new ArrayList<Employee>();
        
        final String sql =
            "SELECT * FROM EMPLOYEE";

        // Spring will do the low-level db access
        List<Map<String, Object>> rawList = jdbcTemplate.queryForList(sql);
        
        // Now transform the raw data into Employee instances
        for(Map<String,Object> map : rawList) {
            Employee e = new Employee();
            e.setFirstName(map.get("FIRSTNAME").toString());
            e.setLastName(map.get("LASTNAME").toString());
            e.setEmail(map.get("EMAIL").toString());
            e.setHireDate((Date)map.get("HIREDATE"));
            employeeList.add(e);
        }
        
        return employeeList;
    }

    @Override
    public void store(Long pk, String lastName, String firstName, String email, Date hireDate) {
         if(pk == null) {
             final String sql = "INSERT INTO EMPLOYEE "
                + "(FIRSTNAME, LASTNAME, EMAIL, HIREDATE, version, dept_id) "
                + "VALUES (?, ?, ?, ?, 1, 1)";
             jdbcTemplate.update(sql, firstName, lastName, email, hireDate);
             
         } else {
             final String sql = "UPDATE EMPLOYEE SET FIRSTNAME=?, "
                + "LASTNAME=?, EMAIL=?, HIREDATE=? WHERE ID=?";
             jdbcTemplate.update(sql, firstName, lastName, email, hireDate, pk);
             
         }
    }

    @Override
    public List<Employee> findPage(List<Employee> empList, int firstRecord, 
        int pageSize, String sortField, SortOrder sortOrder) 
        throws DataAccessException {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        
        if(dbVendor.equalsIgnoreCase("mssql")) {
            sql.append("(SELECT TOP ").append(pageSize).append(" * FROM (");
            sql.append("SELECT TOP ").append(pageSize + firstRecord).append(" * ");
            
        } 

        if(sortField == null) {
            if(dbVendor.equalsIgnoreCase("mssql")) {
                sql.append("FROM EMPLOYEE ORDER BY ID ASC) as foo ORDER BY ID DESC) ");
                sql.append("as bar ORDER BY ID ASC");
                
            } else if(dbVendor.equalsIgnoreCase("mysql")) {
                sql.append("EMPLOYEE ORDER BY ID ASC LIMIT ");
                sql.append(firstRecord).append(", ");
                sql.append(pageSize);
            }
        } else {
            String orderDirTxt = sortOrder.toString(); 
            String orderDir = orderDirTxt.startsWith("ASC") ? "ASC" : "DESC";
            sql.append("FROM EMPLOYEE ORDER BY ").append(sortField);
            if(dbVendor.equalsIgnoreCase("mssql")) {
                sql.append(" ").append(orderDir);
                sql.append(") as foo ORDER BY ");
                sql.append(sortField).append(" ");
                sql.append(orderDir);
                
            } else if(dbVendor.equalsIgnoreCase("mysql")) {
                sql.append(" ").append(orderDir).append(" LIMIT ").append(pageSize);
                sql.append(") as foo ORDER BY ");
                sql.append(sortField).append(" ");
                sql.append(sortOrder.toString()).append(" LIMIT ");
                sql.append(pageSize + firstRecord);
                
            }
            sql.append(") ");
            sql.append("as bar ORDER BY ").append(sortField).append(" ");
            sql.append(orderDir);
        }
        
        System.out.println("*** " + sql.toString());

        empList.clear();

        // Spring will do the low-level db access
        List<Map<String, Object>> rawList = 
                jdbcTemplate.queryForList(sql.toString());
        
        // Now transform the raw data into Employee instances
        for(Map<String,Object> map : rawList) {
            Employee e = new Employee();
            e.setFirstName(map.get("FIRSTNAME").toString());
            e.setLastName(map.get("LASTNAME").toString());
            e.setEmail(map.get("EMAIL").toString());
            e.setHireDate((Date)map.get("HIREDATE"));
            empList.add(e);
        }
        
        return empList;
    }

    public String getDbVendor() {
        return dbVendor;
    }

    public void setDbVendor(String dbVendor) {
        this.dbVendor = dbVendor;
    }
    
    

    @Override
    public int getTotalRecordCount(String table) {
        int num = jdbcTemplate.queryForObject("SELECT COUNT(*) AS NumEmployee FROM EMPLOYEE", Integer.class);
        return num;
    }
    
    public static void main(String[] args) {
        AbstractApplicationContext ctx = 
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IEmployeeDAO dao = (IEmployeeDAO)ctx.getBean("springJDBCEmployeeDAO");
        List<Employee> list = dao.findAll();
        System.out.println(list);
    }
    
}
