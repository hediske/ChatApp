import { Dialog, DialogContent, DialogTitle, GridList, GridListTile, ImageList, Typography, makeStyles } from '@material-ui/core';
import React, { useEffect, useState } from 'react';

interface customProps {
    isOpen : boolean;
    onImageSelect : (imgURL: string) => void;
}

type Images = { id: string; download_url: string };

const useStyles = makeStyles({
  customTitle: {
    height :"100%",
    fontFamily:"Poppins",
    fontSize : "2rem"
  },
});

const  ImageGalleryDialog : React.FC <customProps>= (props) => {
    const {isOpen , onImageSelect} = props; 
    const [images, setImages] = useState<Array<Images>>([]);
    const classes = useStyles();
    

    useEffect(
        () => {
        fetch(
            `https://picsum.photos/v2/list?limit=18&page=${Math.floor(
            Math.random() * 10
            )}`
        )
            .then((res) => res.json())
            .then((data) => {
            setImages(data);
            })
            .catch((error)=> console.log(error));
        }, []);




    return (
        
        <Dialog open={isOpen} keepMounted aria-labelledby="dialog-slide-title">
        <DialogTitle  id="dialog-slide-title">
         <Typography classes={{ root: classes.customTitle }}>
           Select your image avatar
         </Typography>
        </DialogTitle>
        <DialogContent>
          <ImageList cellHeight={160} cols={3}>
            {images.map((img) => (
              <GridListTile
                style={{ cursor: "pointer" }}
                key={img.id}
                cols={1}
                onClick={() => onImageSelect(img.download_url)}
              >
                <img src={img.download_url} alt="Display" />
              </GridListTile>
            ))}
          </ImageList>
        </DialogContent>
      </Dialog>
        
    
    
        );
} 
export default ImageGalleryDialog