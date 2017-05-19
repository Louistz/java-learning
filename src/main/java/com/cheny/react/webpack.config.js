var path = require("path");
var HtmlWebPackPlugin = require('html-webpack-plugin');

var ROOT_PATH = path.resolve(__dirname);
var APP_PATH = path.resolve(ROOT_PATH,'app');
var BUILD_PATH = path.resolve(ROOT_PATH,'build');

module.exports = {
    entry: APP_PATH,

    output: {
        path : BUILD_PATH,
        filename: 'bundle.js'
    },

    plugins:[
        new HtmlWebPackPlugin({
            title:'Hello World app'
        })
    ],

    devServer : {
        historyApiFallback:true,
        hot:true,
        inline:true,
        progress: true,
    }
}