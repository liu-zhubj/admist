<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/11/25
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
   <form action="${website}register" method="post">
       <table>
           <tr>
               <td>
                   用户名:<input type="text" name="userName"/>
               </td>
               <td>密码<input type="text" name="password"/></td>
           </tr>
           <tr>
               <td><input type="submit" value="提交" /></td>
           </tr>
       </table>



   </form>
</body>
</html>
