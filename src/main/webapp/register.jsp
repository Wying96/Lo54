<%-- 
    Document   : registrer
    Created on : 2019-10-16, 0:49:30
    Author     : wuying
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
   
   
     <script src="js/jquery-1.10.2.min.js" ></script>
      <script src="js/bootstrap.min.js"></script>
    <style>
        body{
            background: center center no-repeat;
            background-attachment: fixed;
            background-size: 100% 100%;
            background-color: grey;
        }
        font{
            color: green;
        }
     
    </style>
    </head>
    <body>
        <h1>Hello World!</h1>

           
               
            
        <form class="form-horizontal" action="" method="post" style="display: grid;
grid-template-columns: 50%;
justify-content: center;" >
                        <div class="form-group"  >
                        <label for="username" class="col-md-4 control-label">Username : </label>
                        <div class="col-md-4"><input type="text" class="form-control" id="username" placeholder="input username">
                         </div>
                    
                         </div>
                    
                 
                    
                    <div class="form-group" >
                        <label for="inputpwd" class="col-md-4 control-label">Password : </label>
                        <div class="col-md-4"><input type="password" class="form-control" id="inputpwd" placeholder="enter password">
                        </div>
                    </div>
            <div class="form-group">
                 <label for="confirmepwd" class="col-md-4 control-label">Confirmer : </label>
                        <div class="col-md-4"><input type="password" class="form-control" id="confirmepwd" placeholder="confirmer password">
                        </div>
            </div>
            <div class="form-group">
                 <label for="inputemail" class="col-md-4 control-label">Email : </label>
                        <div class="col-md-4"><input type="email" class="form-control" id="inputemail" placeholder="input email">
                        </div>
            </div>
            <div class="form-group">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <input type="submit" width="75%" value="inscription" name="submit" style="background-color:#cccccc">
                </div>
            </div>
                    
            
            
                    </form>
               
          
                
                
            
  
            
            
      
    </body>
</html>
