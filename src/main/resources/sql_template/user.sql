#namespace("user")
    #sql("getUserByNickNameAndPassWord")
        SELECT * FROM user WHERE name = #p(name);
    #end

    #sql("getUserByPhoneNumber")
        SELECT * FROM user WHERE phoneNumber = #p(phoneNumber);
    #end

    #sql("addUser")
        INSERT INTO user(name, password) VALUES (#p(name), #p(password))));
    #end

    #sql("validateUserByNickNameAndPassWord")
        SELECT * FROM user WHERE nickName = ? AND passWord= ?;
    #end
#end