from PIL import Image
import requests
from requests.auth import HTTPBasicAuth


image = Image.open('P1.jpg','r')
pix_val = list(image.getdata()) 	#list of tuples

pix_val_2 = [list(elem) for elem in pix_val]


#curl 'http://colormind.io/api/' --data-binary '{"input":pix_val_2,"model":"default"}'


payload = '{"input":[[44,43,44],[90,83,82],"N","N","N"],"model":"default"}'
arr='[[44,43,44],[90,83,82],"N","N","N"]'

payload2 = '{"input":' + arr + ',"model":"default"}'
response = requests.post('http://colormind.io/api/',payload2.encode())

foo = response.json()

print(type(foo))

#print(size(foo))

print(foo)