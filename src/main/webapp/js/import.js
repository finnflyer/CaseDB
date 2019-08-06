/**
 * 系统公共JS、CSS文件统一引用入口
 * @author Chill
 * @data 2016-9-13
 */

//系统公共JS文件对象
var common_js_files = [
    'js/table/bootstrap-table.min.js',
    //'js/table/bootstrap-table-zh-CN.min.js',
    //'js/table/bootstrap-table-edit.js',
    //'js/table/bootstrap-select.js'
];
//系统CSS文件对象
var common_css_files = [
    'css/table/bootstrap-table.min.css',
];

/**
 * 导入CSS文件
 */
for(var i = 0; i < common_css_files.length; i++){
    document.write("<link rel='stylesheet' type='text/css' href='"+common_css_files[i]+"'>");
}
/**
 * 导入JS文件
 */
for(var i = 0; i < common_js_files.length; i++){
    document.write("<script type='text/javascript' src='"+common_js_files[i]+"'></script>");
}/**
 * Created by Admin on 2016/9/13.
 */
