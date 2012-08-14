/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var gauge1;
      
window.onload = function(){
  gauge1 = new JustGage({
    id: "gauge1", 
    value: 0, 
    min: 0,
    max: 100,
    title: "Update from Server",
    label: "count",
    shadowOpacity: 1,
    shadowSize: 0,
    shadowVerticalOffset: 10,
    levelColors: [
          "#FFFF00",
          "#FF9900",
          "#FF0000"
        ]    
  });

};

