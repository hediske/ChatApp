import React from "react";


const LoadingScreen : React.FC= () => {
    return (
        <div style={{display:'flex',justifyContent:'center',alignItems:'center',height:'100vh' , background: "rgba(0,0,0,0.5)" , width:"100%" , position:"absolute" , zIndex:"1000" }}>
            <div className="loader"></div>
        </div>);
}
export default LoadingScreen;