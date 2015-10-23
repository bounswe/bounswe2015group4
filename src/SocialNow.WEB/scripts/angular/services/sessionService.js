app.service('sessionService', function(roles) {
    this.userInfo = {
        username: '',
        role: undefined,
        isAuthenticated: false
    }
})
