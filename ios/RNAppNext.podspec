
Pod::Spec.new do |s|
  s.name         = "RNAppNext"
  s.version      = "1.0.0"
  s.summary      = "RNAppNext"
  s.description  = <<-DESC
                  Appnext support for **Android** and comming soon to **iOS**
                   DESC
  s.homepage     = "https://github.com/jerson/react-native-appnext"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "jeral17@gmail.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/jerson/react-native-appnext.git", :tag => "master" }
  s.source_files  = "RNAppNext/**/*.{h,m,a}"
  s.requires_arc = true


  s.dependency "React"
  s.framework = 'QuartzCore'
  s.framework = 'Security'
  s.framework = 'CFNetwork'
  s.framework = 'AVFoundation'
  s.framework = 'SystemConfiguration'
  s.framework = 'MobileCoreServices'
  s.framework = 'CoreGraphics'
  s.framework = 'CoreMedia'
  s.framework = 'MobileCoreServices'
  s.framework = 'AdSupport'
  s.framework = 'UIKit'
  s.framework = 'Foundation'
  s.framework = 'CoreTelephony'

end

  