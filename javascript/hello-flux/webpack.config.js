var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');

     
 module.exports = {
     entry: './app/index.jsx',
     output: {
         path: path.resolve(__dirname, 'dist'),
         filename: 'bundle.js'
     },
     module: {
         rules: [
             {
                 test: /\.js$|\.jsx$/,
                 loader: 'babel-loader',
                 query: {
                     presets: ['env', 'react']
                 }
             }
         ]
    },
     stats: {
         colors: true
     },
     plugins: [new HtmlWebpackPlugin({title: "hello Flux", template: "index.html"})]
     ,devtool: 'source-map'
 };
