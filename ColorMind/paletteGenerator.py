from PIL import Image
import os
import requests
from requests.auth import HTTPBasicAuth
import numpy as np
import matplotlib.pyplot as plt
from skimage import io

f_name='P1'


#Load Image
image = Image.open(open('utils/'+f_name+'.jpg','rb'))
#print(image.size
image = image.resize((255,255),Image.ANTIALIAS)
#print(image.size)
#image.save("P2_scaled.jpg",quality=95)


#Generate pixel
pix_val = list(image.getdata()) 	#list of tuples
pix_val_2 = [list(elem) for elem in pix_val]


#curl 'http://colormind.io/api/' --data-binary '{"input":pix_val_2,"model":"default"}'
#payload1 = '{"input":[[44,43,44],[90,83,82],"N","N","N"],"model":"default"}'
#s0 = '[[44,43,44],[90,83,82],[90,83,81]]'

#Prepare payload
s = str(pix_val_2)
payload = '{"input":' + s + ',"model":"default"}'
print(type(payload))


#Call API
response = requests.post('http://colormind.io/api/',payload.encode())

#Parse Result
responseJson = response.json()
print(responseJson)

result = responseJson.get('result');
print(result)



#Displaying the palette
# palette = np.array(result, dtype=np.uint8)
# ind = [[0,1,2,3,4]]
# ind=np.array(ind)
# plt.imshow(palette[ind])

#Saving the palette
#io.imsave(''+f_name+'_palette.jpg',palette[ind])


plt.show()