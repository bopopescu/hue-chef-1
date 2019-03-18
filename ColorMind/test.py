from PIL import Image
image = Image.open('P1.jpg','r')
pix_val = list(image.getdata()) #list of tuples

pix_val_2 = [list(elem) for elem in pix_val]
#print(pix_val)

#print(type(pix_val_2[0]))

#curl 'http://colormind.io/api/' --data-binary '{"input":pix_val_2,"model":"default"}'

import requests
from requests.auth import HTTPBasicAuth
payload = {'input':pix_val_2}
response = requests.get('http://colormind.io/api/',params=payload)

foo = response.json()

print(type(foo))

print(size(foo))

print(foo)