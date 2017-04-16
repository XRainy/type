/******************************************************************
 * easyUI自定义文本框验证
 *
 * @author zoutuo
 * @version 2013-06-22
 *
 * history:
 *
 */
$.extend($.fn.validatebox.defaults.rules, {
    //验证最小长度
    minLength: {
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: '输入的长度不能少于{0}'
    },
    //验证最大长度
    maxLength: {
        validator: function (value, param) {
            return value.length <= param[0];
        },
        message: '输入的长度不能多于{0}'
    },
    //验证数字范围
    checkNumRange: {
        validator: function (value, param) {
            if (!isNaN(value)) {
                var param1 = param[0];
                var param2 = param[1];
                if (value >= param1 && value <= param2) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        },
        message: "请输入数字，并且范围在{0}~{1}之间"
    }
});

/**
 * $('#xxx').validatebox('remove');移除验证
 * $('#xxx').validatebox('reduce');恢复验证
 */
$.extend($.fn.validatebox.methods, {
    remove: function (jq, newposition) {
        return jq.each(function () {
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
        });
    },

    reduce: function (jq, newposition) {
        return jq.each(function () {
            var opt = $(this).data().validatebox.options;
            $(this).addClass("validatebox-text").validatebox(opt);
        });
    }
});