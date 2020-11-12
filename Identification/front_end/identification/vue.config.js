module.exports = {
    devServer: {
      proxy: {
        '^/api': {
          //target: 'http://10.56.12.173:8080',
          target: 'http://localhost:8081',
          pathRewrite: {'^/api': ''},
          changeOrigin: true,
          logLevel: "debug",
        },
      },
    },
};