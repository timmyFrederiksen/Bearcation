import axios from 'axios'

const USERS_REST_API_URL = 'http://localhost:80/user/users';

class VerificationService {
    getUsers(){
        return axios.get(USERS_REST_API_URL);
    }
}



export default new VerificationService();