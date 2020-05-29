import React, {useState} from "react";
import "./Form.css"
import axios from "axios";
import Spinner from "./Spinner";
import Result from "./Result";


function Form() {

    const [value, setValue] = useState("");
    const [loading, setLoading] = useState(false);
    const [response, setResponse] = useState({});
    const [resultActive, setResultActive] = useState(false);

    const handleOnChange = (e) => {
        setValue(e.value);
    }

    const handleSubmit = () => {
        if (value) {
            setLoading(true);
        }
        setResultActive(false)
        axios.post('http://localhost:8080', {
            longUrl: value
        })
            .then(function (response) {

                setResponse(response.data.shortUrl);
                setLoading(false);
                setResultActive(true)
            })
            .catch(function (error) {
                setLoading(false)
                setResponse(null)
                setResultActive(true)

            });

    }

    return (
        <div>
            <div className="container">
                <form>
                    <div className="field" tabIndex="1">
                        <label htmlFor="URL">
                            Your URL
                            <i className="far fa-user"></i>
                        </label>
                        <input name="username" type="text" placeholder="e.g. www.Ushort.com" required
                               onChange={(e) => handleOnChange(e.target)}
                               value={value}/>
                    </div>
                    <button type="reset" onClick={handleSubmit}>get your URL</button>

                </form>


            </div>


            <div className="result-container">
                {loading ? <Spinner/> : null}
                {resultActive ? <Result shortUrl={response}/> : null}
            </div>
        </div>


    );

}


export default Form;