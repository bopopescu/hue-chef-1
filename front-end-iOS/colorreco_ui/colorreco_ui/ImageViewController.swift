//
//  ImageViewController.swift
//  colorreco_ui
//
//  Created by Ojas B N on 22/04/19.
//  Copyright © 2019 Ojas B N. All rights reserved.
//

import UIKit
import Foundation
import CoreGraphics
import QuartzCore



class ImageViewController: UIViewController {
    var ans = UIColor(red: 1, green: 165/255, blue: 0, alpha: 1)
    
    @IBOutlet weak var back: UIButton!
    
    @IBOutlet weak var choose: UIButton!
    
    @IBOutlet weak var hueChef: UIButton!
    @IBOutlet weak var chosenColorView: UIView!
    
    @IBOutlet weak var imgView: UIImageView!
    
    @IBOutlet weak var chooseButton: UIButton!
    
    var imagePicker = UIImagePickerController()
    

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        let touch = event?.allTouches?.first
        
        let location: CGPoint? = touch?.location(in: touch?.view)
        let px : CGFloat = location!.x
        let py : CGFloat = location!.y
        
        let color = getPixelColorAtPoint(point: location!, sourceView: imgView)
       // print(color)
        ans = color
        chosenColorView.backgroundColor = ans
        

        
    }
    func getPixelColorAtPoint(point:CGPoint, sourceView: UIImageView) -> UIColor{
        
        let pixel = UnsafeMutablePointer<CUnsignedChar>.allocate(capacity: 4)
        let colorSpace = CGColorSpaceCreateDeviceRGB()
        let bitmapInfo = CGBitmapInfo(rawValue: CGImageAlphaInfo.premultipliedLast.rawValue)
        let context = CGContext(data: pixel, width: 1, height: 1, bitsPerComponent: 8, bytesPerRow: 4, space: colorSpace, bitmapInfo: bitmapInfo.rawValue)
        var color: UIColor? = nil
        
        if let context = context {
            context.translateBy(x: -point.x, y: -point.y)
            sourceView.layer.render(in: context)
            
            color = UIColor(red: CGFloat(pixel[0])/255.0,
                            green: CGFloat(pixel[1])/255.0,
                            blue: CGFloat(pixel[2])/255.0,
                            alpha: CGFloat(pixel[3])/255.0)
            
            //pixel.deallocate(capacity: 4)
        }
        return color!
    }
  

    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let image = UIImage(named: "newbackground") {
            view.backgroundColor = UIColor(patternImage: image)
        }
        self.imgView.layer.borderWidth = 1
        self.imgView.layer.borderColor = UIColor.lightGray.cgColor
        
        choose.layer.cornerRadius = 10
        choose.layer.shadowColor = UIColor.black.cgColor
        choose.layer.shadowOffset = CGSize(width: 5, height: 5)
        choose.layer.shadowRadius = 5
        choose.layer.shadowOpacity = 1.0
        
        back.layer.cornerRadius = 10
        back.layer.shadowColor = UIColor.black.cgColor
        back.layer.shadowOffset = CGSize(width: 5, height: 5)
        back.layer.shadowRadius = 5
        back.layer.shadowOpacity = 1.0
        
        hueChef.layer.cornerRadius = 10
        hueChef.layer.shadowColor = UIColor.black.cgColor
        hueChef.layer.shadowOffset = CGSize(width: 5, height: 5)
        hueChef.layer.shadowRadius = 5
        hueChef.layer.shadowOpacity = 1.0
        
        chosenColorView.layer.cornerRadius = 6
        chosenColorView.layer.shadowColor = UIColor.black.cgColor
        chosenColorView.layer.shadowOffset = CGSize(width: 5, height: 5)
        chosenColorView.layer.shadowRadius = 5
        chosenColorView.layer.shadowOpacity = 1.0
        
       

    }
    
  
    
    
    @IBAction func hueChefPressed(_ sender: Any) {
        performSegue(withIdentifier: "imageToDisplay", sender: self)
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?)
    {
        if segue.destination is DisplayViewController
        {
            let vc = segue.destination as? DisplayViewController
            vc?.ipcolor = ans
        }
    }
    
    
    @IBAction func chooseButtonPressed(_ sender: Any) {
        
        let alert = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
        alert.addAction(UIAlertAction(title: "Take Photo", style: .default, handler: { _ in
           self.openCamera()
        }))
        
        alert.addAction(UIAlertAction(title: "Choose Photo", style: .default, handler: { _ in
           self.openGallery()
        }))
        
        alert.addAction(UIAlertAction.init(title: "Cancel", style: .cancel, handler: nil))
        
        //If you want work actionsheet on ipad then you have to use popoverPresentationController to present the actionsheet, otherwise app will crash in iPad
        switch UIDevice.current.userInterfaceIdiom {
        case .pad:
            alert.popoverPresentationController?.sourceView = sender as? UIView
            alert.popoverPresentationController?.sourceRect = (sender as AnyObject).bounds
            alert.popoverPresentationController?.permittedArrowDirections = .up
        default:
            break
        }
        
        self.present(alert, animated: true, completion: nil)
    }
    @IBAction func backButtonPressed(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    /*
    // MARK: - Navigation
     
     

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    
    func openCamera(){
        if(UIImagePickerController .isSourceTypeAvailable(UIImagePickerController.SourceType.camera)){
            imagePicker.sourceType = UIImagePickerController.SourceType.camera
            //If you dont want to edit the photo then you can set allowsEditing to false
            imagePicker.allowsEditing = true
            imagePicker.delegate = self as? UIImagePickerControllerDelegate & UINavigationControllerDelegate
            self.present(imagePicker, animated: true, completion: nil)
        }
        else{
            let alert  = UIAlertController(title: "Warning", message: "You don't have camera", preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
    }
    
    func openGallery(){
        imagePicker.sourceType = UIImagePickerController.SourceType.photoLibrary
        //If you dont want to edit the photo then you can set allowsEditing to false
        imagePicker.allowsEditing = true
        imagePicker.delegate = self as! UIImagePickerControllerDelegate & UINavigationControllerDelegate
        self.present(imagePicker, animated: true, completion: nil)
    }

}
extension ImageViewController:  UIImagePickerControllerDelegate, UINavigationControllerDelegate{
    internal func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        /*
         Get the image from the info dictionary.
         If no need to edit the photo, use `UIImagePickerControllerOriginalImage`
         instead of `UIImagePickerControllerEditedImage`
         */
        guard let selectedImage = info[.originalImage] as? UIImage else {
            fatalError("Expected a dictionary containing an image, but was provided the following: \(info)")
        }
        imgView.image = selectedImage
        
      


        
        //Dismiss the UIImagePicker after selection
        picker.dismiss(animated: true, completion: nil)
    }
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        picker.isNavigationBarHidden = false
        self.dismiss(animated: true, completion: nil)
    }
}





