from PIL import Image
import os
import requests
from requests.auth import HTTPBasicAuth
import numpy as np
import matplotlib.pyplot as plt
from skimage import io
import glob
from argparse import *





def main():

	input_parser = ArgumentParser()
	input_parser.add_argument('-i', '-dir', required=True, nargs=1, metavar=('f1'))
	parser=input_parser.parse_args()
	inputdir=parser.i
	
	out ={}


	images = glob.glob('data/raw_images/'+inputdir[0]+'/*.jpg')
	path = os.getcwd()

	outputpath= path+'/data/reduced_images/'+inputdir[0]
	os.mkdir(outputpath)
	
	

	for image in images:
		with open(image,'rb') as file:
			file_name=image.split('/')[3].split('.')[0]
			img=Image.open(file)
			img=img.resize((255,255),Image.ANTIALIAS)
			img.save(outputpath+'/'+file_name+'.jpg','JPEG')
			
			pix_val = list(img.getdata())
			pix_val_2 = [list(elem) for elem in pix_val]
			out[file_name]=pix_val_2
	#print out


if __name__ == '__main__':
    main()		
