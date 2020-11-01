import Cookies from 'js-cookie'

var username = 'u'
var email = 'e'
var address = 'a'
var role = 'r'

export function getUsername() {
    return Cookies.get(username)
}
  
export function setUsername(name) {
    return Cookies.set(username, name)
}
  
export function removeData() {
    Cookies.remove(username)
    Cookies.remove(email)
    Cookies.remove(address)
    Cookies.remove(role)
    return
}

export function getEmail() {
    return Cookies.get(email)
}
  
export function setEmail(e) {
    return Cookies.set(email, e)
}

export function getAddress() {
    return Cookies.get(address)
}
  
export function setAddress(a) {
    return Cookies.set(address, a)
}

export function getRole() {
    return Cookies.get(role)
}
  
export function setRole(r) {
    return Cookies.set(role, r)
}