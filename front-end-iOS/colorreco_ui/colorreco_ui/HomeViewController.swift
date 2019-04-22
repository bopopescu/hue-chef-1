//
//  HomeViewController.swift
//  colorreco_ui
//
//  Created by Ojas B N on 22/04/19.
//  Copyright © 2019 Ojas B N. All rights reserved.
//

import UIKit

class HomeViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBAction func chooseImagePressed(_ sender: Any) {
        performSegue(withIdentifier: "homeToImage", sender: self)
    }
    
    @IBAction func captureImagePressed(_ sender: Any) {
        performSegue(withIdentifier: "homeToCamera", sender: self)
    }

    @IBAction func chooseColorPressed(_ sender: Any) {
        performSegue(withIdentifier: "homeToColor", sender: self)
    }
    @IBAction func unwindToHome(segue:UIStoryboardSegue) { }
    
}

