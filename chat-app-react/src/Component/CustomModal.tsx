import React from "react";
import { Modal, makeStyles } from "@material-ui/core";
import Fade from "@material-ui/core/Fade";
import { Paper } from "@material-ui/core";

interface CustomNodeProps {
    open : boolean;
    handleClose : () => void;
    children : React.ReactNode;
    handleConfirm ?: () => void;
}

const useStyles = makeStyles((theme) => ({
    modal: {
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
    },
    paper: {
      backgroundColor: theme.palette.background.paper,
      border: "2px solid #000",
      boxShadow: theme.shadows[5],
      padding: theme.spacing(2),
      height:'20vw',
      width:'24vw',
      borderRadius : "15px",
      display : "flex",
      flexDirection:"column",
      justifyContent : "center",      
    },
  }));

  

const  CustomModal : React.FC<CustomNodeProps> = (props) => {
    const classes = useStyles();
    const {open , handleClose, children} = props;
    return (
        <div>
            <Modal className={classes.modal}
                aria-labelledby="transition-modal-title"
                aria-describedby="transition-modal-description"
                open={open}
                onClose={handleClose}
                closeAfterTransition
            >
                <Fade in={open}>
                    <Paper className={classes.paper}>{children}
                        {props.handleConfirm && <button className="button" onClick={props.handleConfirm}>Confirm</button>}
                    </Paper>
                </Fade>

            </Modal>
        </div>
    )

} 

export default CustomModal;