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

class DisplayViewController: UIViewController {
    var ipcolor = UIColor(red: 1, green: 165/255, blue: 0, alpha: 1)
    var opcolor = UIColor(red: 1, green: 165/255, blue: 0, alpha: 1)
    
    
    
    let APP_URL = "https://color-recommender-8980.appspot.com/GetReco?"
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        chosenColorView.backgroundColor = ipcolor
        //outputColorView1.backgroundColor = opcolor
       // outputColorView2.backgroundColor = opcolor
       // outputColorView3.backgroundColor = opcolor
        
        var color : (red: CGFloat, green: CGFloat, blue: CGFloat, alpha: CGFloat)
        color = chosenColorView.backgroundColor!.rgba
        let r = Int(round(color.red * 255))
        let g  = Int(round(color.green * 255))
        let b = Int(round(color.blue * 255))
        
        let payload : String  = "\(r)|\(g)|\(b)"
        //print(payload)
        
        getColorData(url: APP_URL,pl: payload)
        
        
        
        // Do any additional setup after loading the view.
    }
    
    
    
    @IBOutlet weak var chosenColorView: UIView!
    @IBOutlet weak var outputColorView1: UIView!
    
    @IBOutlet weak var outputColorView2: UIView!
    
    @IBOutlet weak var outputColorView3: UIView!
    
    @IBAction func backButtonPressed(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func homeButtonPressed(_ sender: Any) {
        performSegue(withIdentifier: "unwindToHome", sender: self)
    }
    
    func getColorData(url:String , pl:String){
        
//        Alamofire.request(url, method: .get, parameters: parameters).responseJSON {
//            response in
//            if response.result.isSuccess{
//                let colorJSON : JSON = JSON(response.result.value!)
//                print(colorJSON)
//                //Handle output
//
//            }
//            else{
//                print("Error:\(String(describing: response.result.error))")
//            }
//        }
   
       

    
     Alamofire.request("https://color-recommender-8980.appspot.com/GetReco?", method: .get, parameters: ["color":pl])
            .responseString { response in
                //print(response.request as Any)  // original URL request
                //print(response.response as Any) // URL response
                print(response.result.value as Any)   // result of response serialization
        }
        
//        Alamofire.request(String(api)).responseString{response in debugPrint(response.result.value)
//            }
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
