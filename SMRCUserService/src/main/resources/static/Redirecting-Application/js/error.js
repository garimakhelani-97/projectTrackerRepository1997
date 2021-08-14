$(document).ready(function() {
	//http://localhost:8011/users-ws/sso/generateToken?winUserId=kkkk&domain=MIND&sessionId=8987777&callback=abc
    var BASE_URL = "http://localhost:8011";//backend url
    var UI_BASE_URL = "http://localhost:4200";//ui url
    var loginUserName = "mdm-admin1";
    var loginUserDomain = "mind";
    $("button").click(function() {
        function checkApplicationConnectivityAndLogin() {
            $.ajax({
                url : BASE_URL,
                success : function(result, status, xhr) {
                    if (xhr.status == 200 && xhr.readyState)
                        generateTokenAndLogin();
                    $("#div1").html(status);
                },
                error : function(xhr, status, error) {
                    alert(xhr.statusText);
                }
            });
        }
               
        function generateTokenAndLogin() {
            $.ajax({
            url : BASE_URL+"/users-ws/sso/generateToken?winUserId="+loginUserName+"&domain="+loginUserDomain,
            success : function(result, status, xhr) {
                if (xhr.status == 200 && xhr.readyState)
                    ssoLoginAndOpenApplication(result);
            },
            error : function(xhr, status, error) {
                alert(xhr.statusText);
            }
        });
           
        }
       
        function ssoLoginAndOpenApplication(result) {       
           /* $.ajax({
            url : BASE_URL+"/users-ws/sso/login?UUID="+JSON.parse(result).UUID+"&winUserId="+JSON.parse(result).winUserId+"&domain="+JSON.parse(result).domain,
            success : function(res, status, xhr) {
               
                if (xhr.status == 200 && xhr.readyState)
                    window.open(UI_BASE_URL+'/#/login?UUID='+JSON.parse(result).UUID+'&winUserId='+loginUserName+'&domain='+loginUserDomain);
            },
            error : function(xhr, status, error) {
                alert(xhr.statusText);
            }
        });*/

              //window.open(UI_BASE_URL+'/#/login?UUID='+JSON.parse(result).UUID+'&winUserId='+JSON.parse(result).winUserId+'&domain='+JSON.parse(result).domain);
            
             $.ajax({
            url : BASE_URL+"/users-ws/ssoAuth/sso/authentication?winUserId="+JSON.parse(result).winUserId+"&domain="+JSON.parse(result).domain+"&UUID="+JSON.parse(result).UUID,
            //url : BASE_URL+"/users-ws/ssoAuth/sso/authentication?winUserId="+loginUserName+"&Domain="+loginUserDomain,
            success : function(res, status, xhr) {
               
                if (xhr.status == 200 && xhr.readyState)
                    window.open(UI_BASE_URL+'/#/login?winUserId='+loginUserName+'&jwt='+res.token);
            },
            error : function(xhr, status, error) {
                alert(xhr.statusText);
            }
        });

        }
        //checkApplicationConnectivityAndLogin();
        generateTokenAndLogin();
    });
});