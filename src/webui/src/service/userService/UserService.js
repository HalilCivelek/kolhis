import axios from 'axios'

export class UserService {

    static saveUser(data) {
        return axios.post('http://localhost:8080/api/users/save', data);
    }

}

new UserService()
