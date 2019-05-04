//
//  HomeViewController.swift
//  colorreco_ui
//
//  Created by Ojas B N on 22/04/19.
//  Copyright © 2019 Ojas B N. All rights reserved.
//

import UIKit
import QuartzCore

class HomeViewController: UIViewController {
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
    }
    
    override open var shouldAutorotate: Bool {
        return false
    }
    
    
    @IBOutlet weak var titleView: UIImageView!
    @IBOutlet weak var imageMode: UIButton!
    @IBOutlet weak var colorMode: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let image = UIImage(named: "newbackground") {
            view.backgroundColor = UIColor(patternImage: image)
        }
        
    titleView.image = UIImage(named: "national_park")
        
    colorMode.layer.cornerRadius = 10
    colorMode.layer.shadowColor = UIColor.black.cgColor
    colorMode.layer.shadowOffset = CGSize(width: 5, height: 5)
    colorMode.layer.shadowRadius = 5
    colorMode.layer.shadowOpacity = 1.0
        
    imageMode.layer.cornerRadius = 10
    imageMode.layer.shadowColor = UIColor.black.cgColor
    imageMode.layer.shadowOffset = CGSize(width: 5, height: 5)
    imageMode.layer.shadowRadius = 5
    imageMode.layer.shadowOpacity = 1.0
        
        
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

extension UINavigationController {
    
    override open var shouldAutorotate: Bool {
        get {
            if let visibleVC = visibleViewController {
                return visibleVC.shouldAutorotate
            }
            return super.shouldAutorotate
        }
    }
    
    override open var preferredInterfaceOrientationForPresentation: UIInterfaceOrientation{
        get {
            if let visibleVC = visibleViewController {
                return visibleVC.preferredInterfaceOrientationForPresentation
            }
            return super.preferredInterfaceOrientationForPresentation
        }
    }
    
    override open var supportedInterfaceOrientations: UIInterfaceOrientationMask{
        get {
            if let visibleVC = visibleViewController {
                return visibleVC.supportedInterfaceOrientations
            }
            return super.supportedInterfaceOrientations
        }
    }}
