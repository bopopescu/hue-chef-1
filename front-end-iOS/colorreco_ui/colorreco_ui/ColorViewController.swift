//
//  ColorViewController.swift
//  colorreco_ui
//
//  Created by Ojas B N on 22/04/19.
//  Copyright © 2019 Ojas B N. All rights reserved.
//

import UIKit
import iOS_Color_Picker

class ColorViewController:
UIViewController,FCColorPickerViewControllerDelegate {
    var color = UIColor(red: 1, green: 165/255, blue: 0, alpha: 1)
    var ans = UIColor(red: 1, green: 165/255, blue: 0, alpha: 1)

    
    func colorPickerViewController(_ colorPicker: FCColorPickerViewController, didSelect color: UIColor) {
        self.ans = color
        displayView.backgroundColor = color
        dismiss(animated: true)
        
       // print(ans.rgb())
        
    }
    
    func colorPickerViewControllerDidCancel(_ colorPicker: FCColorPickerViewController) {
        
        dismiss(animated: true)
        
    }
    
    
    
    
    
    @IBOutlet weak var displayView: UIView!
    
    

    
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        
        if let image = UIImage(named: "newbackground") {
            view.backgroundColor = UIColor(patternImage: image)
        }
        
        displayView.backgroundColor = color
        
       
       
        

        // Do any additional setup after loading the view.
    }
 
    
    @IBAction func backButtonPressed(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func chooseColorPressed(_ sender: Any) {
        print("call!!")
        let colorPicker = FCColorPickerViewController.colorPicker()
        colorPicker.color = self.color
        colorPicker.delegate = self
        colorPicker.modalPresentationStyle = .formSheet
        present(colorPicker, animated: true)
        
    }
    
    
    @IBAction func hueChefPressed(_ sender: Any) {
        performSegue(withIdentifier: "colorToDisplay", sender: self)
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?)
    {
        if segue.destination is DisplayViewController
        {
            let vc = segue.destination as? DisplayViewController
            vc?.ipcolor = ans
            
        }
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
extension UIColor {
    
    func rgb() -> (red:Int, green:Int, blue:Int, alpha:Int)? {
        var fRed : CGFloat = 0
        var fGreen : CGFloat = 0
        var fBlue : CGFloat = 0
        var fAlpha: CGFloat = 0
        if self.getRed(&fRed, green: &fGreen, blue: &fBlue, alpha: &fAlpha) {
            let iRed = Int(fRed * 255.0)
            let iGreen = Int(fGreen * 255.0)
            let iBlue = Int(fBlue * 255.0)
            let iAlpha = Int(fAlpha * 255.0)
            
            return (red:iRed, green:iGreen, blue:iBlue, alpha:iAlpha)
        } else {
            // Could not extract RGBA components:
            return nil
        }
    }
}

