#### Steps: 

> - **ImageGenerator.py** => Resizes all the training images to [255 x 255].
> - **PaletteGenerator.py** => Uses ColorMind API to generate palette for the the resized images and store them in a JSON file. 

> - The input files are images present in the data directory that are read by ImageGenerator.py to produce a json file in the format <Image_name1>.jpg, <Image_name2>.jpg
>- {
  "Image_name1" : [ [R,G,B],.......(255X255)],
  "Image_name2" : [ [R,G,B],.......(255X255)]
  }

>- The input files are images present in the data directory that are read by PaletteGenerator.py to produce a 5 tuple palette for every image as json in the output

>- {
  "Image_name1" : [[R,G,B],....5],
  "Image_name2" : [[R,G,B],.....5]
  }
    

> - **Graph
