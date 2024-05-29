import os
import shutil
import zipfile

def copy_dependencies(src_dir, dest_jar):
    # Check if the source directory exists
    if not os.path.exists(src_dir):
        print("Dependencies Folder just does not exist")
        return
    
    # Check if the destination jar exists
    if not os.path.exists(dest_jar):
        print("Client jar does not exist")
        return

    # Open the destination JAR file in append mode
    with zipfile.ZipFile(dest_jar, 'a') as jar:
        # Iterate over files and directories in the source directory
        for root, _, files in os.walk(src_dir):
            for file in files:
                # Get the absolute path of the source file
                src_path = os.path.join(root, file)
                # Calculate the relative path of the file within the JAR
                rel_path = os.path.relpath(src_path, src_dir)
                # Write the file to the JAR preserving its directory structure
                jar.write(src_path, arcname=rel_path)

if __name__ == "__main__":
    basedir = os.path.dirname(os.path.abspath(__file__))
    src_dir = os.path.join(basedir, "libs", "dependencies")
    dest_jar = os.path.join(basedir, "target", "ParadiseClient.jar")
    
    copy_dependencies(src_dir, dest_jar)
