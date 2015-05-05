/**
 * Created by zhangxingjie on 5/5/15.
 */

function register(obj) {
    var _obj = ob.parentNode;
    while(_obj.tagName.toUpperCase() != "FORM"){
        _obj = _obj.parentNode;
        }
    var nickName = _obj.nick_name.value;
    var password_1 = _obj.password_1.value;
    var password_2 = _obj.password_2.value;
    var email = _obj.email.value;
    var gender = _obj.gender.value;
    var birthday = _obj.birthday.value;
    }