import React from "react";


const style : { [key: string]: React.CSSProperties } = {
    loader: {
        height: "100px",
        border: "16px solid #f3f3f3",
        borderRadius: "50%",
        animation: "spin 1s linear infinite",
    }
    
};

const LoadingScreen : React.FC= () => {
    return (
        <div style={{display:'flex',justifyContent:'center',alignItems:'center',height:'100vh'}}>
            <div className="loader"></div>
        </div>);
}
export default LoadingScreen;