### Data Links: 

- [Palette Data](https://drive.google.com/drive/folders/1_M_c9N1AVibTPsMLmpLtMDh5QcdeGz3z)
- [Raw Images](https://drive.google.com/drive/folders/1-n0UTvg-w71Ionb8z8Y1rlqRLT6IwtB4)
- [Reduced Images](https://drive.google.com/drive/folders/1GrrxGBkURgVFawXb0Zfxxe4OVVnNSsp9)

### Flow: 

- Mapper => Refer [here](https://github.com/anicksaha/color-recommender/tree/master/code/mapper-job).
- Reducer => Refer [here](https://github.com/anicksaha/color-recommender/tree/master/code/reducer-job).
    
### Questions & Links:

##### Dataset Link: 
- [Google Drive Link](https://drive.google.com/open?id=1LQkNkKC8-7SnBmD2EqzfsWd2oVBbZvx6)

##### How to run? 

- Download Wallpapers.zip to have a look on Input images in batches of 100 in every directory. 8 in total
- Download Training data to have look at 16 json files. (8 img<x>.json that has the image and 8 pal<x>.json that has palette)
- Place the images from Wallpapers on the data directory.
- Run PaletteGenerator.py and ImageGenerator.py > output.json
