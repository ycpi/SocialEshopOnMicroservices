module.exports = {
    devServer: {
      proxy: {
        '^/api': {
          //target: 'http://10.56.12.173:8080',
          target: 'http://localhost:9401',
          pathRewrite: {'^/api': ''},
          changeOrigin: true,
          logLevel: "debug",
        },
      },
    },
};