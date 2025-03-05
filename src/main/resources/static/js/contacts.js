console.log("Contacts.js");
const baseURL = "http://localhost:8081";
const viewContactModal=document.getElementById('view_contact_modal');

// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};

const contactModal = new Modal(viewContactModal,options, instanceOptions);

// function to open the modal
function openContactModal(){
    contactModal.show();
}

// function to close the modal
function closeContactModal(){
    contactModal.hide();
}

// funtion call to load data on the modal of the contact
async function loadContactData(id){
    console.log(id);
    try {
        const data = await(
            await fetch(`${baseURL}/api/contacts/${id}`)
        ).json();
        console.log(data);
        document.querySelector('#contact_image').src = data.picture;
        document.querySelector('#contact_name').innerHTML = data.name;
        document.querySelector('#contact_email').innerHTML = data.email;
        document.querySelector('#contact_phoneNumber').innerHTML = data.phoneNumber;
        const contactFavorite = document.querySelector("#contact_favorite");
        document.querySelector('#contact_address').innerHTML = data.address;
        document.querySelector('#contact_description').innerHTML = data.description;
        document.querySelector('#contact_gitHubLink').innerHTML = data.gitHubLink;
        document.querySelector('#contact_linkedInLink').innerHTML = data.linkedInLink;
        document.querySelector('#contact_instagramLink').innerHTML = data.instagramLink;
        if (data.favorite) {
            contactFavorite.innerHTML = "Favourite Contact";
          } else {
            contactFavorite.innerHTML = "Not Favorite Contact";
          }
        openContactModal();
    } catch (error) {
        console.log("Error is : ", error);
    }
}

// delete contact
async function deleteContact(id) {
    Swal.fire({
        title: "Do you want to delete the contact?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Delete",
        cancelButtonText: "Cancel",
        buttonsStyling: false, // Disable default styles
        customClass: {
            confirmButton: "px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700 mr-4", // Added spacing
            cancelButton: "px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600"
        }
    }).then((result) => {
        if (result.isConfirmed) {
            // Make an API request to delete the contact
            fetch(`/user/contacts/delete/${id}`, { method: "DELETE" })
                .then(response => {
                    if (response.ok) {
                        Swal.fire({
                            title: "Deleted!",
                            text: "The contact has been removed.",
                            icon: "success",
                            buttonsStyling: false,
                            customClass: {
                                confirmButton: "px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
                            }
                        }).then(() => {
                            window.location.reload(); // Refresh page after deletion
                        });
                    } else {
                        Swal.fire("Error!", "Failed to delete contact.", "error");
                    }
                })
                .catch(() => {
                    Swal.fire("Error!", "Something went wrong.", "error");
                });
        }
    });
}