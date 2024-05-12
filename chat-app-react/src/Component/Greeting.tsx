import React, { useState } from "react";
import Paper from "@material-ui/core/Paper";
import Typopgraphy from "@material-ui/core/Typography";
import TextField from "@material-ui/core/TextField";
import Avatar from "@material-ui/core/Avatar";
import { IconButton, Tooltip } from "@material-ui/core";
import ImageGalleryDialog from "./ImageGalleryDialog";
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  customInputBase: {
    backgroundColor: "#BDBDBD",
    height :"100%",
    
  },
});
const style: { [key: string]: React.CSSProperties } = {
  container : {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    height : "100vh",

  },
  paper: {
    height: "50vh",
    width: "50%",
    backgroundColor: "#4b494c",
    color: "white",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    padding: "20px",
    borderRadius : "15px"
  },
  input: {
    width: "70%",
    color: "white",
    height : "5vh",
    fontFamily : "Poppins !important",
    borderRadius : "7px",


  },
  form: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    gap : "50px",
  },
  avatar: {
    height: 250 ,
    width: 250  ,
    margin: "2rem 0rem",
  },
  typography : {
    fontFamily : "Poppins",
    color : "white",
    fontSize: "2.5vh"
  }
};

interface Props {
  onUsernameEnter: (name: string, avatar: string) => void;
}

const Greeting: React.FC<Props> = (props) => {
  const [name, setName] = useState("");
  const [img, setImage] = useState("");
  const [open, setOpen] = useState(false);
  const { onUsernameEnter } = props;
  const classes = useStyles();

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!name && !img) return;
    onUsernameEnter(name, img);
  };

  const handleImageSelect = (imgURL: string) => {
    if (!imgURL) return;
    setImage(imgURL);
    setOpen(false);
  };

  return (
    <div style={style.container} >
      <Paper style={style.paper} >
        <form onSubmit={handleSubmit} style={style.form}>
          <Typopgraphy  style={style.typography}>
            Please enter your name before joining the chat
          </Typopgraphy>
          <IconButton
            style={style.avatar}
            onClick={() => setOpen((prev) => !prev)}
          >
            <Tooltip title="Add Image">
              <Avatar src={img} style={style.avatar} sizes="large" />
            </Tooltip>
          </IconButton>
          <TextField
            style={style.input}
            classes={{ root: classes.customInputBase }}            
            placeholder="Enter Username..."
            value={name}
            onChange={(e) => setName(e.target.value)}
            InputLabelProps={{
              style: {
                color: "black",
                fontFamily : "Poppins",
              },
            }}
            InputProps={{
              style: {
                color: "black",
                height:"100%",
                fontFamily : "Poppins",
                fontSize : "2vh",
                paddingLeft:"10px"

              },
            }}

          />
        </form>
      </Paper>
      <ImageGalleryDialog isOpen={open} onImageSelect={handleImageSelect} />
    </div>
  );
};

export default Greeting;