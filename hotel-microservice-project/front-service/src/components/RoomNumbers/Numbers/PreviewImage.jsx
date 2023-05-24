import React, { useState } from 'react';


const PreviewImage = ({photoRoom}) => {
    const [preview, setPreview] = useState(null);
    const reader = new FileReader();

    if (photoRoom) {
        reader.readAsDataURL(photoRoom);
    }
    reader.onload = () => {
        setPreview(reader.result);
    }
    
    
    
    return ( 
        <div>
            <img src={preview} alt="preview" width="200px" height="200px" />
        </div>
    );
    
  
};


export default PreviewImage;