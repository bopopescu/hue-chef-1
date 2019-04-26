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
        print("ans")
        print(ans.rgb()!)
        
    }
    
    func colorPickerViewControllerDidCancel(_ colorPicker: FCColorPickerViewController) {
        
        dismiss(animated: true)
        
    }
    
    
    
    
    
    @IBOutlet weak var displayView: UIView!
    
    

    
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        
       
       
        

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
    
    func rgb() -> Int? {
        var fRed : CGFloat = 0
        var fGreen : CGFloat = 0
        var fBlue : CGFloat = 0
        var fAlpha: CGFloat = 0
        if self.getRed(&fRed, green: &fGreen, blue: &fBlue, alpha: &fAlpha) {
            let iRed = Int(fRed * 255.0)
            let iGreen = Int(fGreen * 255.0)
            let iBlue = Int(fBlue * 255.0)
            let iAlpha = Int(fAlpha * 255.0)
            
            //  (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are blue).
            let rgb = (iAlpha << 24) + (iRed << 16) + (iGreen << 8) + iBlue
            return rgb
        } else {
            // Could not extract RGBA components:
            return nil
        }
    }
}

