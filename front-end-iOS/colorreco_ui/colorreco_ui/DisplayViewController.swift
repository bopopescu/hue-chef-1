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
    
    let APP_URL = "https://jsonplaceholder.typicode.com/todos/1"
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        chosenColorView.backgroundColor = ipcolor
        //outputColorView1.backgroundColor = opcolor
       // outputColorView2.backgroundColor = opcolor
       // outputColorView3.backgroundColor = opcolor
        
        getColorData(url: APP_URL,parameters: ["":""])
        
        
        
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
        performSegue(withIdentifier: "unwindSegueToHome", sender: self)
    }
    
    func getColorData(url:String , parameters:[String:String]){
        Alamofire.request(url, method: .get, parameters: parameters).responseJSON {
            response in
            if response.result.isSuccess{
                let colorJSON : JSON = JSON(response.result.value!)
                print(colorJSON)
                //Handle output
                
            }
            else{
                print("Error:\(String(describing: response.result.error))")
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
    
}
