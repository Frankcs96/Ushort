
import axios from "axios";

const sendURL = (url) => {

    axios.post('http://localhost:8080', {
        url: url
    })
        .then((response) => {
            return response;
        }, (error) => {
            return error;
        });

}



export default sendURL
