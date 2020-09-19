module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://192.168.1.105:9090',
                changeOrigin: true,
            }
        }
    }
}
