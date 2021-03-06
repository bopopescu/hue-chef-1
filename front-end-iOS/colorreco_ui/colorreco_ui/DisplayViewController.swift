//
//  DisplayViewController.swift
//  colorreco_ui
//
//  Created by Ojas B N on 22/04/19.
//  Copyright © 2019 Ojas B N. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON
import Foundation

class DisplayViewController: UIViewController {
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
    }
    
    override open var shouldAutorotate: Bool {
        return false
    }
    
    var ipcolor = UIColor(red: 1, green: 165/255, blue: 0, alpha: 1)
    var ipcolor1 = UIColor(red: 123/255, green: 100/255, blue: 0, alpha: 1)
    var opcolor = UIColor(red: 100/255, green: 100/255, blue: 0, alpha: 1)
    
    var color1 = UIColor(red: 0, green: 0, blue: 0, alpha: 1)
    var color2 = UIColor(red: 0, green: 0, blue: 0, alpha: 1)
    var color3 = UIColor(red: 0, green: 0, blue: 0, alpha: 1)
    
    
    var op :String?
    
    
    
    let APP_URL = "https://color-recommender-8980.appspot.com/GetReco?"
    
   
    
    @IBOutlet weak var chosenColorView: UIView!
   
    
    @IBOutlet weak var outputView1: UIView!
    
    @IBOutlet weak var outputView2: UIView!
    
    
    @IBOutlet weak var outputView3: UIView!
    
    @IBOutlet weak var back: UIButton!
    
    @IBOutlet weak var home: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let image = UIImage(named: "newbackground") {
            view.backgroundColor = UIColor(patternImage: image)
        }
        
        
        chosenColorView.backgroundColor = ipcolor
        
        back.layer.cornerRadius = 10
        back.layer.shadowColor = UIColor.black.cgColor
        back.layer.shadowOffset = CGSize(width: 5, height: 5)
        back.layer.shadowRadius = 5
        back.layer.shadowOpacity = 1.0
        
        home.layer.cornerRadius = 10
        home.layer.shadowColor = UIColor.black.cgColor
        home.layer.shadowOffset = CGSize(width: 5, height: 5)
        home.layer.shadowRadius = 5
        home.layer.shadowOpacity = 1.0
        
        chosenColorView.layer.cornerRadius = 6
        chosenColorView.layer.shadowColor = UIColor.black.cgColor
        chosenColorView.layer.shadowOffset = CGSize(width: 5, height: 5)
        chosenColorView.layer.shadowRadius = 5
        chosenColorView.layer.shadowOpacity = 1.0
        
        outputView1.layer.cornerRadius = 6
        outputView1.layer.shadowColor = UIColor.black.cgColor
        outputView1.layer.shadowOffset = CGSize(width: 5, height: 5)
        outputView1.layer.shadowRadius = 5
        outputView1.layer.shadowOpacity = 1.0
        
        outputView2.layer.cornerRadius = 6
        outputView2.layer.shadowColor = UIColor.black.cgColor
        outputView2.layer.shadowOffset = CGSize(width: 5, height: 5)
        outputView2.layer.shadowRadius = 5
        outputView2.layer.shadowOpacity = 1.0
        
        outputView3.layer.cornerRadius = 6
        outputView3.layer.shadowColor = UIColor.black.cgColor
        outputView3.layer.shadowOffset = CGSize(width: 5, height: 5)
        outputView3.layer.shadowRadius = 5
        outputView3.layer.shadowOpacity = 1.0
        
        
        var color : (red: CGFloat, green: CGFloat, blue: CGFloat, alpha: CGFloat)
        color = chosenColorView.backgroundColor!.rgba
        let r = Int(round(color.red * 255))
        let g  = Int(round(color.green * 255))
        let b = Int(round(color.blue * 255))
        let payload : String  = "\(r)|\(g)|\(b)"
        //print(payload)
    
        getColor(input:payload)
       
}
    
    func getColor(input :String) {
        getColorData(pl:input){
            [weak self] response,error in
            
            guard let strongSelf = self else {
                return
            }
            strongSelf.op = response!
            strongSelf.updateUI()
         }
    }
    
    
    func updateUI(){
        print("Thre\(self.op!)")
        
        var colorArr = self.op!.components(separatedBy: "#")
        colorArr.remove(at : 0)
        
        self.color1 = UIColor( red: CGFloat((colorArr[0].components(separatedBy:"|")[0] as NSString).floatValue / 255 ),green: CGFloat((colorArr[0].components(separatedBy:"|")[1] as NSString).floatValue / 255),blue:CGFloat((colorArr[0].components(separatedBy:"|")[2] as NSString).floatValue / 255),alpha:1)
        self.color2 = UIColor( red: CGFloat((colorArr[1].components(separatedBy:"|")[0] as NSString).floatValue / 255 ),green: CGFloat((colorArr[1].components(separatedBy:"|")[1] as NSString).floatValue / 255),blue:CGFloat((colorArr[1].components(separatedBy:"|")[2] as NSString).floatValue / 255),alpha:1)
        self.color3 = UIColor( red: CGFloat((colorArr[2].components(separatedBy:"|")[0] as NSString).floatValue / 255 ),green: CGFloat((colorArr[2].components(separatedBy:"|")[1] as NSString).floatValue / 255),blue:CGFloat((colorArr[2].components(separatedBy:"|")[2] as NSString).floatValue / 255),alpha:1)
        
        
        self.outputView1.backgroundColor = self.color1
        self.outputView2.backgroundColor = self.color2
        self.outputView3.backgroundColor = self.color3
        
   }

    
    @IBAction func backButtonPressed(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    
    
    
    @IBAction func homeButtonPressed(_ sender: Any) {
        performSegue(withIdentifier: "unwindToHome", sender: self)
    }
    
    
    func getColorData(pl:String, completionHandler: @escaping (String?, Error?) -> ()) {
        makeCall(pl: pl, completionHandler: completionHandler)
    }
        
    func makeCall(pl: String, completionHandler: @escaping (String?, Error?) -> ()) {
      
     Alamofire.request("https://color-recommender-8980.appspot.com/GetReco?", method: .get, parameters: ["color":pl])
     .responseString { response in
            switch response.result {
            case .success(let value):
                completionHandler(value as String, nil)
            case .failure(let error):
                completionHandler(nil,error)
            }
       
        }
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
    

extension UIColor {
    var rgba: (red: CGFloat, green: CGFloat, blue: CGFloat, alpha: CGFloat) {
        var red: CGFloat = 0
        var green: CGFloat = 0
        var blue: CGFloat = 0
        var alpha: CGFloat = 0
        getRed(&red, green: &green, blue: &blue, alpha: &alpha)
        
        return (red, green, blue, alpha)
    }
}
