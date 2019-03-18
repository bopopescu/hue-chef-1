from PIL import Image
import requests
from requests.auth import HTTPBasicAuth


image = Image.open('P1.jpg','r')
pix_val = list(image.getdata()) 	#list of tuples

pix_val_2 = [list(elem) for elem in pix_val]


#curl 'http://colormind.io/api/' --data-binary '{"input":pix_val_2,"model":"default"}'

payload1 = '{"input":[[44,43,44],[90,83,82],"N","N","N"],"model":"default"}'

s0 = '[[44,43,44],[90,83,82],[90,83,81]]'
payload2 = '{"input":' + s0 + ',"model":"default"}'

s = str(pix_val_2)
#print(type(s))

payload = '{"input":' + s + ',"model":"default"}'
print(type(payload))

response = requests.post('http://colormind.io/api/',payload2.encode())
responseJson = response.json()
#print(type(responseJson))
result = responseJson.get('result');
print(result)