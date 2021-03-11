import instance from '@/http/request'
import urls from "@/http/urls";

export { login, register, getUserById, modifyUser, deleteUser, logout}

function login(data) {
    return instance.post(urls.login, data).then(res => res.data)
}

function register(data){
    return instance.post('/register', {
        data
    }).then(res =>
        res.data
    )
}

function getUserById(id){
    return instance.get('/logintest/' + id).then(res => res.data)
}

function modifyUser(id, data){
    return instance.put('/register' + id, data)
        .then(res => res.data)
}

function deleteUser(id) {
    return instance.delete('/register/' + id)
        .then( res => res.data)
}

function logout() {
    return instance.post('/auth/logout')
        .then(res => res.data)
}
