package com.bhumil.thebellboy.UtilityClasses;

public class Validations
{
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static int INVALID_EMAIL = 1;
    public static int INVALID_USERNAME = 2;
    public static int INVALID_PASSWORD = 3;
    public static int INVALID_MOBILE = 4;
    public static int VALID_DATA = 0;

    public int ValidateLogin(String email, String password)
    {
        if(email == null || email.isEmpty())
        {
            return INVALID_EMAIL;
        }
        else
        {
            if(email.matches(emailPattern))
            {
                if(password == null || password.isEmpty())
                {
                    return INVALID_PASSWORD;
                }
                else
                {
                    if(password.length()>=4)
                    {
                        return VALID_DATA;
                    }
                    else
                    {
                        return INVALID_PASSWORD;
                    }
                }
            }
            else
            {
                return INVALID_EMAIL;
            }
        }
    }

    public int ValidateSignUp(String username, String email, String mobilenumber, String password )
    {
        int isValidLoginCred = ValidateLogin(email, password);
        if(VALID_DATA == isValidLoginCred)
        {
            if(username == null || username.isEmpty())
            {
                return INVALID_USERNAME;
            }
            else
            {
                if(mobilenumber == null || mobilenumber.isEmpty() || mobilenumber.length() < 10)
                {
                    return INVALID_MOBILE;
                }
                else
                {
                    return VALID_DATA;
                }
            }
        }
        else
        {
            return isValidLoginCred;
        }
    }
}
