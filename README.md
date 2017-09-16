
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
  
  locate the index page: {rootPath}/index
  
  create a new user: {rootPath}/register 
    args:{
       userName:xxx,
       passWord:xxx
    }
     


  

