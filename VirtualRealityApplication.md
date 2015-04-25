# Virtual Reality Application Development for Android #

> As the technology improves, virtual reality devices as Oculus Rift has become more common.  Developpers wanted to push it further and bring it to mobile devices. This in mind, they have started making products like Gear VR, ZEISS VR ONE and in the simplest form Google Cardboard.

> Before getting into the app development, let me give you some information about how the technology works with these devices. These goggles have no display built-in, they are using the display of your mobile device. To give you 3D feeling they are bending the images from your mobile device’s display through lenses.

![http://cdn.slashgear.com/wp-content/uploads/2014/10/zeiss-vr-one-5.png](http://cdn.slashgear.com/wp-content/uploads/2014/10/zeiss-vr-one-5.png)

Mobile device goes into the tray

![http://root.innoactive.de/c/zeiss/vrone/example1.jpg](http://root.innoactive.de/c/zeiss/vrone/example1.jpg)

(Pre Distortion)

![http://root.innoactive.de/c/zeiss/vrone/example2.jpg](http://root.innoactive.de/c/zeiss/vrone/example2.jpg)

(With Distortion)

> This way your brain is able to merge two 2D images into 3D. All the computations of the application is done by mobile device. Most of the goggles doesnt have any sensors on it, except Gear VR, so you have  to use sensors from mobile device, such as Accelerator and Gyrometer.


# How does Application Development works? #

  * Most of the goggle producers offer a SDK with the device. SDKs include Headtracking, Gesture Controls, Distortion etc. You can use these SDKs or can implement your own, its all up to you. For example [Oculus Vr](https://developer.oculus.com/downloads/#sdk=mobile), [Zeiss VROne](https://bitbucket.org/vrone/unity3d).
  * A 3D modelling program if you are willing to do your own 3D models, but if you don’t have any artistic skill or time you can simply use free and paid models from Unity Asset Store. For example [Blender](http://www.blender.org/)(free), 3DsMax.
  * Most important off all is [Unity](http://unity3d.com/) 3D, a 2D/3D game engine, in which you design the application.


Unity offers you an easy environment to develop in. If you simplify the process in Unity;
  * Create “Game Objects”, which interact with other things in your application, such as player, enemies or just a button.
  * Attach scripts to “Game Objects”, which has the functionalities you coded in it. Unity uses Javascript and C# as programming language.
  * Add some animation, further tweaking and brushing.

Unity has lots of [tutorials](http://unity3d.com/learn/tutorials/modules) available in their website and a very helpful [community](http://answers.unity3d.com/).

After you are finished with the application, you can simply build it for android and install it to your device with .apk file.

[GO BACK TO ANDROID PAGE](BuildingAndroidApplication.md)