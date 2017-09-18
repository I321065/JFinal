
softwares:
      tomcat 
      mysql 
      navicat--mysql UI 
      idea

framework:JFinal

API:
  rootPath: host:8080/ironman

  locate to login page: {rootPath}/login
     args:{
        userName:xxx,
        passWord:xxx,
        publicKey:xxx
     }
     
     return:{
        data:{
           userName:xxx
           token:xxx      
        }
        errorNum:xxx
        errorMsg:xxx
     }
  
  locate the index page: {rootPath}/index
  
  create a new user: {rootPath}/register 
    args:{
       userName:xxx,
       passWord:xxx
    }
    
    
    
    
    
    
    
    
    
  
  data returned format:
    {
       data:{
          xxxx;
          xxxx;
          ....      
       },
       
       errorNum:0,
       
       errorMsg:""
         
    }
     


  

