import React, {useState} from 'react';
import {InputText} from "primereact/inputtext";
import {InputTextarea} from "primereact/inputtextarea";
import {Dropdown} from "primereact/dropdown";
import {Button} from "primereact/button";
import {UserService} from "../service/userService/UserService";

export const User = () => {

    let User = {
        id: null,
        firstName: '',
        lastName: '',
        password: '',
        userName: '',
        emailAddress: '',
        phoneNumber: '',
        status: '',
        address: {
            address: '',
            city: '',
            district: '',
        }
    }

    const [user, setUser] = useState(User);
    const [address, setAddress] = useState(User.address);
    const [city, setCity] = useState(User.address.city);
    const [status, setStatus] = useState(User.status);
    const [district, setDistrict] = useState(User.address.district);
    const [userType, setUserType] = useState(User.userType);
    const dropdownItems = [
        {name: 'Admin', code: 'ADMIN'},
        {name: 'Müşteri', code: 'CUSTOMER'}
    ];

    const cities = [
        {name: 'Admin', code: 'ADMIN'},
        {name: 'Müşteri', code: 'CUSTOMER'}
    ]
    const districts = [
        {name: 'Admin', code: 'ADMIN'},
        {name: 'Müşteri', code: 'CUSTOMER'}
    ]

    const statusItems = [
        {name: 'AKTIF', code: '1'},
        {name: 'PASIF', code: '0'}
    ];

    const saveUser = () => {
        debugger;
        user.address.city = city.code;
        user.address.district = district.code;
        user.address.address = address;
        UserService.saveUser(user).then(response => {
            debugger;
        })
    }


    const onUserChange = (e, name) => {
        const val = (e.target && e.target.value) || '';
        let _user = {...user};
        _user[`${name}`] = val;
        setUser(_user);
        console.log(user)
    }


    return (
        <div className="p-grid">
            <div className="p-col-12">
                <div className="card">
                    <h5>Kullanıcı Bilgileri</h5>
                    <div className="p-fluid p-formgrid p-grid">
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="firstName">İsim</label>
                            <InputText id="firstName" onChange={(e) => onUserChange(e, 'firstName')} type="text" required={true}/>
                        </div>
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="lastName">Soyisim</label>
                            <InputText id="lastName" onChange={(e) => onUserChange(e, 'lastName')} type="text" required={true}/>
                        </div>
                        <div className="p-field p-col-12">
                            <label htmlFor="password">Şifre</label>
                            <InputText id="password" onChange={(e) => onUserChange(e, 'password')} type="text" required={true}/>
                        </div>
                        <div className="p-field p-col-12">
                            <label htmlFor="address">Adres</label>
                            <InputTextarea id="address" onChange={(e) => setAddress(e.target.value)} rows="4" required={true}/>
                        </div>
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="userName">Kullanıcı Adı</label>
                            <InputText id="userName" onChange={(e) => onUserChange(e, 'userName')} type="text" required={true}/>
                        </div>
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="emailAddress">E-mail Adres</label>
                            <InputText id="emailAddress" onChange={(e) => onUserChange(e, 'emailAddress')} pattern="email" type="email" required={true}/>
                        </div>
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="phoneNumber">Telefon Numarası</label>
                            <InputText id="phoneNumber" onChange={(e) => onUserChange(e, 'phoneNumber')} type="text" required={true}/>
                        </div>
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="city">İl</label>
                            <Dropdown id="city" value={city} onChange={(e) => setCity(e.value)} on options={cities} optionLabel="name" placeholder="Select One" required={true}></Dropdown>
                        </div>
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="district">İlçe</label>
                            <Dropdown id="district" value={district} onChange={(e) => setDistrict(e.value)} options={districts} optionLabel="name" placeholder="Select One" required={true}></Dropdown>
                        </div>
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="userType">Kullanıcı Tipi</label>
                            <Dropdown id="userType" value={userType} onChange={(e) => setUserType(e.value)} options={dropdownItems} optionLabel="name" placeholder="Select One" required={true}></Dropdown>
                        </div>
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="status">Kullanıcı Durumu</label>
                            <Dropdown id="status" value={status} onChange={(e) => setStatus(e.value)} options={statusItems} optionLabel="name" placeholder="Seç" required={true}></Dropdown>
                        </div>
                    </div>
                    <div className="p-grid p-justify-end">
                        <Button label="Kaydet" onClick={saveUser} style={{width: '100'}} className="p-button-raised p-mr-3 p-mb-5"/>
                    </div>
                </div>

            </div>
        </div>
    );
}
