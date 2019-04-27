//
//  DisplayViewController.swift
//  colorreco_ui
//
//  Created by Ojas B N on 22/04/19.
//  Copyright © 2019 Ojas B N. All rights reserved.
//

import UIKit

class DisplayViewController: UIViewController {
    var ipcolor = UIColor(red: 1, green: 165/255, blue: 0, alpha: 1)

    override func viewDidLoad() {
        super.viewDidLoad()
        chosenColorView.backgroundColor = ipcolor
        

        // Do any additional setup after loading the view.
    }
   
    
    
    @IBOutlet weak var chosenColorView: UIView!
    
    @IBAction func backButtonPressed(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func homeButtonPressed(_ sender: Any) {
        performSegue(withIdentifier: "unwindSegueToHome", sender: self)
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
