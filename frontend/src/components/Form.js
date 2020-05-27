import React, {useState} from "react";
import "./Form.css"
import sendUrl from "../services/sendURL"


function Form() {

    const [value, setValue] = useState("");

    const handleOnChange = (e) => {
        setValue(e.value);
    }

    const handleSubmit = (e) => {


    }

    return (

        <div className="container">
            <form>
                <div className="field" tabIndex="1">
                    <label htmlFor="URL">
                        <i className="far fa-user"></i>Your URL
                    </label>
                    <input name="username" type="text" placeholder="e.g. www.Ushort.com" required
                           onChange={(e) => handleOnChange(e.target)}
                           value={value}/>
                </div>
                <button type="reset" onClick={handleSubmit}>get your URL</button>

            </form>
        </div>
    );

}


export default Form;