Term project for CSCI-8980 (Topics in Mobile Computing).

> [Course overview](https://www-users.cs.umn.edu/~fengqian/8980_spring19.pdf)


### What is the problem you plan to solve/measure?

The problem we are trying to solve is to use ML algorithms(NN) to solve the problem of choosing harmonious color pattern (by taking advantage of pre-ranked(trained) color palettes) for an object of given color scheme/palette. 

The use cases for the problem are many-
- Ability to choose the article with matching color while buying personal accessories both online and offline. 
- Assisting users with color blindness to effectively make choice of accessory purchase decisions based on the application.
- Enabling users to make smart decisions by recommending them the list of colors that is essential for them based on their tagging of existing wardrobe colors.
< … more use cases will be explored and added >

> __Future work:__ The high level approach can be further extended to the Augmented Reality world, by showing the changed scenes at real time to the user.


### What are the key challenges?

- Gathering high number of training set to ensure that the algorithm is stable to produce consistent color palette for the given color.
- The inherent quantization of colors that happens through the performance of camera for different mobile devices 
- Analysing the complexity of the algorithm on different mobile devices and tuning a model that does performs with accuracy across different device specifications.
- Another inherent challenge owing the complex model is how to design a distributed architecture to minimize energy and GPU usage both at client and server side
- Making the architecture robust to work in different bandwidth across different network.
- Ranking strategy of the best shade based on a high number of user tagging. 
- Determining a suitable classifier to classify the input palette to the nearest palette.
- Determining a suitable validation set to improve the accuracy of the model.


### What is the high-level approach you plan to take?

We will resolve the issues described in the challenges section and define the problem more formally. 
A basic client-server architecture with suitable adaptation is figured. 

 Server-
The color palettes are obtained through training a General Adversarial Network. GANs are different from traditional discriminative networks used to classify data. Using GAN, a n-shade color palette is determined where n is hyperparameter to be tuned. 
The test data of image is passed matched classified as to the nearest palette based on a chosen classifier and resulting complementary colors output to the user.
The model used to get the recommended palette train the model on the fly based on the palette chosen by the user.

Client-
The client situated in the mobile captures the image using camera interface to be processed for the match.
The client interacts with the server on two interactions majorly. Once, when it requests the results for the processed image and again, when the desired choice of palette is ranked.
The chosen palette is later, stored offline for further usage.
The application is later analyzed on the following metrics- network latency, mobile infrastructure and accuracy of the model to be tested on existing mobile GAN benchmarks used in apps like FaceApp.
Validation of the application usability is ascertained through features like A/B testing.

### Expected results:

An application that improves the relevance of the suggestions based on the model being used and a model with accuracy better than existing standards.
Comparing the system performance of mobile based GAN used with existing ones like CycleGAN and check if we have advanced the state-of the art in this aspect.

</br>

- Collaboration is done via GitHub. 
- A detailed task breakdown with individual responsibilities and roadmap can be found [here](https://github.com/anicksaha/csci-8980/blob/master/_md-files/progress.md).

</br>

_The tentative timelines are as follows (high level task breakdown) -_
</br> </br>
1) __February- Mid March__ - Setting up the basic client and server for the application. Training data dump. Getting the MVP up.
2) __Second half of March - Mid April__ - Testing the application to accommodate different use cases. Hyperparameter tuning on the discriminative network to get better matches. 
3) __April- Deadline__ - Performance analysis for different network modes(LTE,WiFi), energy computation and GPU usage optimization and hyperparameter tuning for the purpose. Final report compilation with test cases.


### Links and References: 

- YouTube: [Here](https://www.youtube.com/watch?v=U2f0vZ5cHF4)
