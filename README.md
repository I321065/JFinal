
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
        username:xxx,
        password:xxx,
        publicKey:xxx
     }
     
     return:{
        data:{
           username:xxx
           token:xxx      
        }
        errorNum:xxx
        errorMsg:xxx
     }
  
  locate the index page: {rootPath}/index
  
  create a new user: {rootPath}/register 
    args:{
       username:xxx,
       password:xxx
    }
    
    
  create a new article: {rootPath}/article/save 
     args:{
         title:xxx,
         content:xxx
     }
      
  list all articles: {rootPath}/article/list 
     args:{
        
     }
     
  list all articles that the argument is user's userId: {rootPath}/article/list 
     args:{
        articleUserId:xxx
     }
     
  delete a article:{rootPath}/article/delete
      args:{
         articleId:1
      }
      
  add a comment for a article:{rootPath}/article/comment
      args:{
         articleId:1
         userId:xxx  (I can get this value from seesion after we open the login function
         commentOverall:[0,1,2] //good is 1, bad is 2, default is 0          
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
     


  

