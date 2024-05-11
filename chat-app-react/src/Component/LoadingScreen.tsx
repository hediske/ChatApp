import React from "react";


const style : { [key: string]: React.CSSProperties } = {
    loader: {
        position: "absolute",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
        width: "100px",
        height: "100px",
        border: "16px solid #f3f3f3",
        borderRadius: "50%",}
};

const LoadingScreen : React.FC= () => {
    return (
        <div>
            <div className="loader"></div>
        </div>);
}
export default LoadingScreen;