import React from "react";
import "./Result.css"


const Result = (props) => {
    let domain = "http://localhost:8080/";
    let shortUrl = props.shortUrl;
    let fullUrl = domain + shortUrl;





    return (

        <div className="result">
            {shortUrl ? <h1>Your URL: <a rel="noopener noreferrer" target="_blank" href={fullUrl}>{fullUrl}</a></h1>  :
                <h1>You must enter a valid URL</h1>}



        </div>

    );
}


export default Result